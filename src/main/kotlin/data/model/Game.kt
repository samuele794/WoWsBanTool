package data.model

import annotation.NoArg
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey

@NoArg
@DynamoDbBean
data class WarshipMatch(
    @get:DynamoDbPartitionKey
    @set:DynamoDbAttribute("matchId")
    var matchId: String
) {
}