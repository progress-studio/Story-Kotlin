package kr.progress.story.story

import kr.progress.story.parser.XMLBody
import kr.progress.story.parser.XMLDecodable
import kr.progress.story.parser.XMLEncodable
import kr.progress.story.parser.XMLNode

data class Character(
    val id: String,
    val show: Boolean? = null,
    val body: List<Intent>? = null
) : Intent(), XMLEncodable {

    companion object : XMLDecodable<Character> {
        override fun invoke(node: XMLNode): Character {
            val id = node.attributes["id"]!!
            val show = node.attributes["show"]?.toBooleanStrictOrNull()
            return when (node.body) {
                is XMLBody.Children -> {
                    Character(
                        id,
                        show,
                        node.body.body.map { Intent(it) }
                    )
                }

                else -> {
                    Character(id, show)
                }
            }
        }
    }

    override fun toXMLNode(): XMLNode {
        val show = show?.let {
            mapOf("show" to it.toString())
        } ?: emptyMap()
        return XMLNode(
            "character",
            show,
            body?.let {
                XMLBody.Children(
                    body.map { it.toXMLNode() }
                )
            }
        )
    }
}
