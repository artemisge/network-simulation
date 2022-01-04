# importing the required module
import matplotlib.pyplot as plt
 
# x axis values
prob = [0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,1]
# corresponding y axis values
avgdelay = [1.7406524466750313, 4.809566156386966, 11.392394166497873, 15.35302946915495, 16.872623479771654, 17.975631905554714, 18.20508736124247, 18.55831129297869, 18.87369530309113, 18.81021897810219]
throughput = [0.797, 1.5743, 1.9748, 1.9987, 2.0145, 1.9821, 2.0089, 2.0039, 1.9928, 2.0139]
pckloss = [0.0, 0.019904210984636437, 0.18237484484898633, 0.3761490760649403, 0.4984714800546788, 0.5828572628749055, 0.6402859696157284, 0.6866992306371822, 0.7229061133856082, 0.7477875]

avgdelay = [x for _,x in sorted(zip(throughput, avgdelay))]
throughput = sorted(throughput)
print(throughput)
print(avgdelay)
# plotting the points
plt.plot(throughput, avgdelay)
 
# naming the x axis
plt.xlabel('probability')
# naming the y axis
plt.ylabel('packet loss rate')
 
# giving a title to my graph
plt.title('C')
 
# function to show the plot
plt.show()