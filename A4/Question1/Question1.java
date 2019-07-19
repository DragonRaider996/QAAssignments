import java.lang.StringBuilder;

public class Question1 {
	private String getH1Header(String data) {
		return "<H1>" + data + "</H1>\n";
	}

	private String getH2Header(String data) {
		return "<H2>" + data + "</H2>";
	}

	private String getParagraph(String data) {
		return "<P>" + data + "</P>\n";
	}

	private String getAnchor(String href, String data) {
		return "<A HREF=\"" + href + "\">" + data + "</A>\n";
	}

	public String buildBookQuoteHTML() {
		StringBuilder builder = new StringBuilder();

		// Build header.
		builder.append(getH1Header("A page of famous book quotes!"));
		builder.append(getH2Header("- Some book reviewer"));
		// Add book quotes.
		builder.append(getParagraph("I know. I was there. I saw the great void in your soul, and you saw mine."));
		builder.append(getParagraph(
				"She says nothing at all, but simply stares upward into the dark sky and watches, with sad eyes, the slow dance of the infinite stars."));
		builder.append(getParagraph(
				"Clocks slay time… time is dead as long as it is being clicked off by little wheels; only when the clock stops does time come to life."));
		builder.append(getParagraph(
				"None of those other things makes a difference. Love is the strongest thing in the world, you know. Nothing can touch it. Nothing comes close. If we love each other we’re safe from it all. Love is the biggest thing there is."));
		builder.append(getParagraph(
				"Sometimes we get sad about things and we don’t like to tell other people that we are sad about them. We like to keep it a secret. Or sometimes, we are sad but we really don’t know why we are sad, so we say we aren’t sad but we really are."));
		builder.append(getParagraph("I know not all that may be coming, but be it what it will, I’ll go to it laughing."));
		builder.append(getParagraph("Maybe ever’body in the whole damn world is scared of each other."));
		builder.append(getParagraph(
				"Life is to be lived, not controlled; and humanity is won by continuing to play in face of certain defeat."));
		builder.append(getParagraph("It's the possibility of having a dream come true that makes life interesting."));
		builder.append(getParagraph(
				"I cannot fix on the hour, or the spot, or the look or the words, which laid the foundation. It is too long ago. I was in the middle before I knew that I had begun."));
		builder.append(getParagraph("There is no greater agony than bearing an untold story inside you."));
		builder.append(getParagraph(
				"Still, there are times I am bewildered by each mile I have traveled, each meal I have eaten, each person I have known, each room in which I have slept. As ordinary as it all appears, there are times when it is beyond my imagination."));
		builder.append(getParagraph("And so we beat on, boats against the current, borne back ceaselessly into the past."));
		builder.append(getParagraph(
				"Hello, babies. Welcome to Earth. It's hot in the summer and cold in the winter. It's round and wet and crowded. At the outside, babies, you've got about a hundred years here. There's only one rule that I know of, babies—God damn it, you've got to be kind."));
		builder.append(getParagraph(
				"He stepped down, trying not to look long at her, as if she were the sun, yet he saw her, like the sun, even without looking."));
		builder.append(getParagraph(
				"How wonderful it is that nobody need wait a single moment before starting to improve the world."));
		// Build footer.
		builder.append(getAnchor("page2.html", "Click here to go to the next page!"));
		return builder.toString();
	}
}