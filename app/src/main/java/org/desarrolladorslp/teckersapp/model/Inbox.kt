package org.desarrolladorslp.teckersapp.model

data class Inbox(var highPriority :ArrayList<MessageHeader>, var lowPriority: ArrayList<MessageHeader>)