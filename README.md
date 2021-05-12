# AutomatedWorkspace
 
Automation of the work of a small enterprise to work with the warehouse.
There are several groups of goods (for example: Food, non-food ...). 
In each group of goods there are specific goods (for example: flour, buckwheat ...). 
Each product has the following properties - name, description, manufacturer, quantity in stock, price per unit.
The group of goods contains the following properties - name, description.
Realization of:
 1) graphical user interface
 2) Saving data to file / files. There is a file that contains the names of all groups. 
 Goods from each group are in a separate file.
 3) Name of goods - unique (can not be found in any group of products).
 4) The name of the product group is unique.
 5) Adding / editing / deleting a product group - when deleting a product group, delete all products.
 6) Adding / editing / deleting goods to a group of goods (meaning the name, description, manufacturer, unit price).
 7) Interface of adding goods (came to the warehouse of buckwheat - 10 pieces), 
	 the interface of writing off goods (sold buckwheat - 5 pcs.)
 8) Product search.
 9) Output of statistical data: output of all goods with information of the warehouse, 
 	output of all goods on the group of goods with information, 
 	the total cost of goods in the warehouse (quantity * on the price), 
 	the total cost of goods in the group of goods.
  
  Main File: AutomatedWorkspace.java
  Author: Zasukha Dasha
