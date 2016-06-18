# IOT-Node

# Project Purpose 
The project Purpose is to add a base layer for any IOT application by providing a specefied semantic that organise and identify
the data and provide the application with a remote cloud server that hold all the needed data and also a local server (edison) 
that allow communication between sensors localy and widly set the communication between rules specified by the user and
measurments of sensors . It also allow more than on application to use the same sensor and take different measurment from it 
withoud needing to buy a new sensor to reconfigure it to be used to read certin measurments . 

# Organizing Data
Different Entities are stored as different datasets rather that different graphs in the same dataset to make the query
more simple and avoid specifing the graphs name each time I query or SPARQL will perform the query on the Default graph . 
This way of organizing data allow us to add more complex entity in on dataset and seprate it to different graphs , where each
graph acts as a container for a specified partition of this complex entity
