# importing the required module
import matplotlib.pyplot as plt
 
# x axis values
prob = [0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,1]
# corresponding y axis values
avgdelay = [4.963448449710109, 8.902477388910736, 12.874251497005988, 15.36224642750075, 17.017607042817126, 17.797435383690644, 18.31471826749549, 18.666197607799777, 18.939133945694962, 18.896462122726366]
throughout = [0.7934, 1.5258, 1.8704, 2.0014, 1.9992, 1.9964, 1.9948, 1.9898, 1.9814, 2.0012]
pckloss = [0.0037660055234747677, 0.0620012277470841, 0.2133512244213351, 0.3719722100519497, 0.5006967947441768, 0.5820017522633402, 0.6437190759263873, 0.6882123341139734, 0.7235071722450795, 0.7489]

 
# plotting the points
plt.plot(prob, pckloss)
 
# naming the x axis
plt.xlabel('probability')
# naming the y axis
plt.ylabel('packet loss rate')
 
# giving a title to my graph
plt.title('C')
 
# function to show the plot
plt.show()