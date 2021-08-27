package data.model

import annotation.NoArg
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey

@NoArg
@DynamoDbBean
data class Tournament(
    @get:DynamoDbPartitionKey
    @set:DynamoDbAttribute("TournamentId")
    var tournamentId: String,
    @set:DynamoDbAttribute("tournamentName")
    var tournamentName: String
)

data class WoWsGame(
    @set:DynamoDbAttribute("gameId")
    var gameId: String,
    @set:DynamoDbAttribute("TournamentId")
    var tournamentId: String
)