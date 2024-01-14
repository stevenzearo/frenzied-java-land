import datetime

import matplotlib.pyplot as plt
import seaborn
from matplotlib import animation

import data


def to_grouped_data(lang_data):
    grouped_data = {}  # [{"python": {"2001-01-30": 12.12, "2001-02-28": 12.23}}]
    for item in lang_data:
        lang = item["name"]
        lang_records = item["data"]
        lang_record_map = {}
        for record in lang_records:
            date = datetime.datetime.fromtimestamp(int(str(record[0])[:-3]))
            lang_record_map[date] = record[1]
        grouped_data[lang] = lang_record_map
    return grouped_data


fig, ax = plt.subplots()

grouped_data = to_grouped_data(data.data2)
names = grouped_data.keys()

index = set()
for v in grouped_data.values():
    for time_key in v.keys():
        index.add(time_key)
index = list(index)
index.sort()


def animate(i):
    # 清空当前图形
    ax.cla()

    new_rate_ls = []
    record_time = index[i]
    record_time_str = record_time.strftime("%Y-%m-%d")
    for k, v in grouped_data.items():
        score = v.get(record_time, 0)
        new_rate_ls.append((k, score))

    new_rate_ls.sort(key=lambda x: x[1])

    sorted_names = list(map(lambda x: x[0], new_rate_ls))
    rates = list(map(lambda x: x[1], new_rate_ls))
    # 重新绘制条形图
    bars = ax.barh(sorted_names, rates, color=seaborn.color_palette('hls', len(names)))

    # 设置标题、轴标签等属性
    ax.set_title('Language Ranking ' + record_time_str)
    ax.set_xlabel('Score')
    ax.set_ylabel('Language')
    ax.set_xlim(0, 30)
    for i in range(len(new_rate_ls)):
        x = new_rate_ls[i][1]
        ax.text(x, i, '%.2f%%' % x, fontsize=10, ha='left', va='center')

    return bars


if __name__ == '__main__':
    ani = animation.FuncAnimation(fig, animate, frames=range(len(index)), interval=200)
    ani.to_html5_video()
    ani.save("test1.mp4")
