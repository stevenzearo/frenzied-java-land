import os

if __name__ == '__main__':
    isGoOn = True
    while (isGoOn):
        os.system("echo Hello, word!")
        os.system("echo hello")
        user_input = input("Need to stop? Y/N\n")
        if (user_input.lower() == "Y".lower()):
            isGoOn = False
