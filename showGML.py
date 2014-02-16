# WorldFS
# run on Python 3.3 x86 on Windows 7 x64
# package required: iGraph 0.7, pycario 1.10
# you can find lib binaries here http://www.lfd.uci.edu/~gohlke/pythonlibs/

from igraph import *

#demandCapacity
g = Graph.Read_GML("demandCapacityGML.txt");
layout = g.layout("grid_fr")

visual_style = {}
visual_style["vertex_size"] = 20
visual_style["vertex_color"] = "#FFFF00"
visual_style["layout"] = layout

color_dict = {"0": "black", "1": "blue", "2":"green", "3":"red"}
visual_style["edge_color"] = [color_dict[weight] for weight in g.es["value"]]

visual_style["bbox"] = (600, 600)


plot(g, target="demandCapacity.png", **visual_style)

#unitCost
g = Graph.Read_GML("unitCostGML.txt");
layout = g.layout("grid_fr")

visual_style = {}
visual_style["vertex_size"] = 20
visual_style["vertex_color"] = "#FFFF00"
visual_style["layout"] = layout

color_dict = {"1": "red", "300": "black"}

visual_style["edge_color"] = [color_dict[weight] for weight in g.es["value"]]



visual_style["bbox"] = (600, 600)

edge_width_dict = {"1":0, "300": 1}
visual_style["edge_width"] = [edge_width_dict[weight] for weight in g.es["value"]]
plot(g, target="unitCost1.png", **visual_style)


edge_width_dict = {"1":1, "300": 0}
visual_style["edge_width"] = [edge_width_dict[weight] for weight in g.es["value"]]
plot(g, target="unitCost2.png", **visual_style)
