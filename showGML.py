# WorldFS
# run on Python 2.7.3 on Windows 7 x64

from igraph import *

g = Graph.Read_GML("demandCapacityGML.txt");
#layout = g.layout("kk")
#plot(g, layout = layout)

visual_style = {}
visual_style["vertex_size"] = 20
visual_style["vertex_color"] = "#FFFF00"


#visual_style["edge_label"] =  g.es["label"]

visual_style["bbox"] = (1024, 1024)
plot(g, **visual_style)






