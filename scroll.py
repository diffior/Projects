import pyautogui

speed= input('how fast do you want to scroll? (in seconds) ')
sleepTime = input('how long do you want to scroll? (in seconds) ')

pyautogui.time.sleep(3)


#makes an infinite loop to keep scrolling
try:
    while True:
        pyautogui.scroll(int(speed))
        pyautogui.time.sleep(int(sleepTime))
except KeyboardInterrupt:
    print('Done')


#for the first input to scroll down put a negative number for how fast you want to scroll
#for the second input what is the rate you want to scroll
#press any key to stop the program
