from math import log, floor, pi

def stirling(n):
    return floor( ((n+0.5)*log(n) - n + 0.5*log(2*pi))/log(10) ) 


for num in range(100001,1000001):
    print(str(num)+"-"+ str(stirling(num)))
