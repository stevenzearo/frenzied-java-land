import json

from django.http import HttpRequest, HttpResponse


def hello(request: HttpRequest):
    data_bytes = request.body.decode("utf-8")
    print("data_bytes: %s\n" % data_bytes)
    if (data_bytes is str()):
        return HttpResponse("hello, world!")
    data: dict = json.loads(data_bytes)
    name = data["name"]
    text ="world" if (name is None) else "name"
    return HttpResponse("hello, %s!" % text)
