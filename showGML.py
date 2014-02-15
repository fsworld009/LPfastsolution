# WorldFS
# run on Python 2.7.3 on Windows 7 x64

from igraph import *

g = Graph.Read_GML("unitCostGML.txt");
layout = g.layout("grid_fr")
#plot(g, layout = layout)

visual_style = {}
visual_style["vertex_size"] = 20
visual_style["vertex_color"] = "#FFFF00"
visual_style["layout"] = layout


empty_list = []


#color_dict = {"0": "black", "1": "blue", "2":"green", "3":"red"}
#visual_style["edge_color"] = [color_dict[weight] for weight in g.es["value"]]

color_dict2 = {"1": "red", "300": "black"}
visual_style["edge_color"] = [color_dict2[weight] for weight in g.es["value"]]


#visual_style["edge_label"] =  g.es["value"]

visual_style["bbox"] = (1024, 1024)
plot(g, **visual_style)






