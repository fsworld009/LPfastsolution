# WorldFS
# run on Python 2.7.3 on Windows 7 x64

from igraph import *

#g = igraph.Graph.Read_GML("demandCapacityGML.txt");

g = Graph()
g.add_vertices(3)
g.add_edges([(0,1), (1,2)])
print g
layout = g.layout("circle")
plot(g, layout = layout)
