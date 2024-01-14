import datetime
import random

import matplotlib.pyplot as plt
from matplotlib import animation

import data

language_data = data.data

# 定义数据
names = ['Alice', 'Bob', 'Charlie']
scores = [85, 92, 76]
name_score_map = {"Alice": 85, "Bob": 92, "Charlie": 76}

fig, ax = plt.subplots()
bar_width = 0.35
index = range(len(names))


def animate(i):
    # 清空当前图形
    ax.cla()

    new_score_ls = []
    for k, v in name_score_map.items():
        score = v + random.randrange(-20, 20)
        new_score_ls.append((k, score, get_color(k)))


    for d in language_data:
        name = d['name']
        lang_data = d['data']



    new_score_ls.sort(key=lambda x: x[1])

    names = list(map(lambda x: x[0], new_score_ls))
    scores = list(map(lambda x: x[1], new_score_ls))
    colors = list(map(lambda x: x[2], new_score_ls))
    # 重新绘制条形图
    bars = ax.barh(names, scores, color=colors)

    # 设置标题、轴标签等属性
    ax.set_title('Python Ranking')
    ax.set_xlabel('Score')
    ax.set_ylabel('Name')
    # ax.set_xticks([])
    # ax.set_yticklabels(['{:d}'.format(int(tick)) for tick in ax.get_yticks()])

    return bars
def get_color(name):
    if name == "Bob":
        return '#FF0000'  # 红色
    elif name == "Alice":
        return '#00FF00'  # 绿色
    elif name == "Charlie":
        return '#FFFF00' # 黄色
    else:
        return "#FFFFFF"
    pass

if __name__ == '__main__':
    ani = animation.FuncAnimation(fig, animate, frames=range(10), interval=1000)
    ani.to_html5_video()
    ani.save("test.mp4")