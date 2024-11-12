import RPi.GPIO as GPIO
import time
sensor = 7
led= 3
GPIO.setmode(GPIO.BOARD)
GPIO.setup(sensor,GPIO.IN)
GPIO.setup(led,GPIO.OUT)
GPIO.output(led,False)
print ("IR sensor ready..")
print (" ")
try:
    while True:
        if GPIO.input(sensor):
            GPIO.output(led,True)
            print ("Object detected")
            while GPIO.input(sensor):
                time.sleep(0.2)
        else:
            GPIO.output(led,False)
            
except KeyboardInterrupt:
    print("Not Working")
    GPIO.cleanup()

