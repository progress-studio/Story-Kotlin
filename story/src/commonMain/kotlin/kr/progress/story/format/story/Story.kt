package kr.progress.story.format.story

import kr.progress.story.parser.XMLBody
import kr.progress.story.parser.XMLDecodable
import kr.progress.story.parser.XMLEncodable
import kr.progress.story.parser.XMLNode

data class Story(
    val body: List<Intent>
) : XMLEncodable {
    companion object : XMLDecodable<Story> {
        override operator fun invoke(node: XMLNode): Story {
            val body = node.body as XMLBody.Children
            return Story(
                body = body.body.map { Intent(it) }
            )
        }
    }

    override fun toXMLNode(): XMLNode {
        return XMLNode(
            tag = "story",
            body = XMLBody.Children(
                body.map { it.toXMLNode() }
            )
        )
    }
}