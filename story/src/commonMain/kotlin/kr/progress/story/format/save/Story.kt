package kr.progress.story.format.save

import kr.progress.story.parser.Identifiable
import kr.progress.story.parser.XMLDecodable
import kr.progress.story.parser.XMLEncodable
import kr.progress.story.parser.XMLNode

data class Story(
    override val id: String
) : XMLEncodable, Identifiable {
    companion object : XMLDecodable<Story> {
        override operator fun invoke(node: XMLNode): Story {
            return Story(
                id = node.attributes["id"]!!
            )
        }
    }

    override fun toXMLNode(): XMLNode {
        return XMLNode(
            tag = "story",
            attributes = mapOf("id" to id)
        )
    }
}