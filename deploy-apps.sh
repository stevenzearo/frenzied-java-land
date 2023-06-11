#!/bin/bash

if [ ! -n "$1" -o ! -n "$2" ]; then
  echo "Can not continue, please pass app name and port"
  exit 1
fi

is_runnable_app() {
  app_names=("registry-site","ichat-site","ichat-service")
  for i in "${app_names[@]}"
  do
    [ "$i" == "$app_name" ] && return "1";
  done
  return "0"
}

[ "$(is_runnable_app)" == "0" ] && echo "can not run this app"

app_name="$1"
port="$2"
current_path=$(pwd)
echo "Current path '$current_path'"

get_pid() {
  echo "$(lsof -i:${port} | awk 'BEGIN {for (i=1;i<3;i++) {getline;pid=$2}} END {print pid}')"
}

pid=$(get_pid)

get_resource_path() {
  echo $app_name | grep -q -E '\-service$' && echo "./backend/${app_name}/build/distributions/${app_name}.tar"
  echo $app_name | grep -q -E '\-site$' && echo "./${app_name}/build/distributions/${app_name}.tar" 
}

echo "resource path:$(get_resource_path)"

kill_exist_app() {
  if [ -n "$pid" ]; then
    echo "Existing app runing on pid:$pid"
    kill $pid
    echo "Killed $pid"
  fi
  return 1
}

deploy_app() {
  echo "Starting deploy app: $1"
  rm -rf ./deployment/${app_name}
  cp $(get_resource_path) ./deployment
  cd ./deployment/
  tar -xvf ./${app_name}.tar
  cd ${app_name}/
  
  nohup ./bin/${app_name} > ${app_name}.log 2>&1 &
  echo "Waiting startup..."
  sleep 5s
  
  new_pid=$(get_pid)
  echo "new pid: ${new_pid}"
  if [ ! -n "$new_pid" ]; then
    echo "Failed to start app:${app_name}"
    exit 1
  else
    echo "Successfully start app: [${app_name}] on port [${port}], pid:[${new_pid}]"
    echo "Deploy app: [$app_name] success!"
    exit 0
  fi
  
}
kill_exist_app
deploy_app $1
