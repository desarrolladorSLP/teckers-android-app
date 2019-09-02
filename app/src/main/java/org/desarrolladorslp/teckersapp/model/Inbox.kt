package org.desarrolladorslp.teckersapp.model

data class Inbox(var highPriority :List<MessageHeader>, var lowPriority: List<MessageHeader>)