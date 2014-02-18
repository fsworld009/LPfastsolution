# WorldFS
# run on Python 3.3 x86 on Windows 7 x64
# package required: iGraph 0.7, pycario 1.10
# you can find lib binaries here http://www.lfd.uci.edu/~gohlke/pythonlibs/

from igraph import *

visual_style = {}
visual_style["vertex_size"] = 30
visual_style["vertex_color"] = "#FFFF00"
visual_style["edge_arrow_size"] = 0.7
visual_style["edge_arrow_width"] = 0.7

visual_style["bbox"] = (800, 800)

#demandCapacity
g = Graph.Read_GML("demandCapacityGML.txt");
layout = g.layout("grid_fr")
visual_style["layout"] = layout


color_dict = {"0": "black", "1": "blue", "2":"green", "3":"red"}
ls = [color_dict[weight] for weight in g.es["value"]]
visual_style["edge_color"] = ls
visual_style["edge_arrow_color"] = ls



plot(g, target="demandCapacity.png", **visual_style)

#unitCost
g = Graph.Read_GML("unitCostGML.txt");
layout = g.layout("grid_fr")

color_dict = {"1": "red", "300": "black"}

ls = [color_dict[weight] for weight in g.es["value"]]
visual_style["edge_color"] = ls
visual_style["edge_arrow_color"] = ls

plot(g, target="unitCost.png", **visual_style)



#plannedCost
g = Graph.Read_GML("plannedCostGML.txt");
layout = g.layout("grid_fr")
#print(g.es["value"])
ls = ["black" if int(x)>=300 else "red" for x in g.es["value"]]
visual_style["edge_color"] = ls
visual_style["edge_arrow_color"] = ls

plot(g, target="plannedCost.png", **visual_style)

#plannedCapacity
g = Graph.Read_GML("plannedCapacityGML.txt");
layout = g.layout("grid_fr")
#print(g.es["value"])
#ls = ["black" if int(x)>=300 else "red" for x in g.es["value"]]
ls = ["red" if int(x)>=30 else "blue" if int(x)>=20 else "green" if int(x)>=10 else "black" for x in g.es["value"]]
visual_style["edge_color"] = ls
visual_style["edge_arrow_color"] = ls
plot(g, target="plannedCapacity.png", **visual_style)
