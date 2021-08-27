package data

import data.model.Tournament
import it.github.samuele___.BanTool.AWS_ACCESS_KEY
import it.github.samuele___.BanTool.AWS_SECRET_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.DescribeTableRequest
import software.amazon.awssdk.services.dynamodb.model.StreamSpecification
import software.amazon.awssdk.services.dynamodb.model.StreamViewType
import software.amazon.awssdk.services.dynamodb.streams.DynamoDbStreamsClient
import software.amazon.awssdk.services.kinesis.KinesisClient


class AwsDynamoRepository {
    //https://docs.aws.amazon.com/streams/latest/dev/kcl2-standard-consumer-java-example.html
    private val awsCredentials = AwsBasicCredentials.create(
        AWS_ACCESS_KEY,
        AWS_SECRET_KEY
    ).run {
        StaticCredentialsProvider.create(this)
    }

    private val awsRegion = Region.EU_SOUTH_1

    val dynamoClient = DynamoDbClient.builder()
        .region(awsRegion)
        .credentialsProvider(awsCredentials)
        .build()

    val enhancedClient = DynamoDbEnhancedClient.builder()
        .dynamoDbClient(dynamoClient)
        .build()

    val dynamoStreamClient = DynamoDbStreamsClient.builder()
        .credentialsProvider(awsCredentials)
        .region(awsRegion)
        .build()


    suspend fun getTournamentById(tournamentId: String) = withContext(Dispatchers.IO) {
        val tournamentTable: DynamoDbTable<Tournament> =
            enhancedClient.table("Tournament", TableSchema.fromBean(Tournament::class.java))

        dynamoClient

        return@withContext tournamentTable.getItem {
            it.key {
                it.partitionValue(tournamentId)
            }
        }
    }

    suspend fun listenWarshipGameById(warshipMatchId: String) {
//        val kinesisClient  = KinesisClient.builder()
//            .region(awsRegion)
//            .credentialsProvider(awsCredentials)
//            .build()

//        kinesisClient.createStream {
//
//        }
    }
}