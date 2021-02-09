EndPoint to send the data to queue:  http://localhost:8080/sales/sendMessage 
Message Type 1:

{
    "messageType": "MT1",
    "product" : "orange",
    "price" : 3
}

Message Type 2:

{
    "messageType": "MT2",
    "product" : "orange",
    "price" : 3,
    "occurences" : 5
}

Message Type 3:

{
    "messageType": "MT3",
    "product" : "orange",
    "price" : 3,
    "operation" : "multiply"
}
