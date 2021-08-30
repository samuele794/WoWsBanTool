package data.model

import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.*
import javax.imageio.ImageIO

data class Clan(
    val clanId: String,
    var clanName: String,
    var clanTag: String,
    var clanIcon: String
){
    fun encodeClanIcon(clanIconImage: BufferedImage){
        val outputStream = ByteArrayOutputStream()
        ImageIO.write(clanIconImage, "png", outputStream)

       clanIcon = Base64.getEncoder().encodeToString(outputStream.toByteArray())
    }

    fun getClanIconDecoded(): BufferedImage{
        val clanIconDecoded = Base64.getDecoder().decode(clanIcon)

        return ImageIO.read(ByteArrayInputStream(clanIconDecoded))
    }
}