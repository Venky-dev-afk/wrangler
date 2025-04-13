package io.cdap.wrangler.parser;

import io.cdap.wrangler.api.parser.ByteSize;
import io.cdap.wrangler.api.parser.TimeDuration;
import io.cdap.wrangler.parser.DirectivesLexer;
import io.cdap.wrangler.parser.DirectivesParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParserTest {

    @Test
    public void testByteSizeParsing() {
        String input = "directive :10KB";
        try {
            CharStream stream = CharStreams.fromString(input);
            DirectivesLexer lexer = new DirectivesLexer(stream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            DirectivesParser parser = new DirectivesParser(tokens);
            parser.setErrorHandler(new org.antlr.v4.runtime.BailErrorStrategy()); // Handle errors
            ParseTree tree = parser.statements(); // Assuming 'statements' is the entry point

            // Assert that parsing was successful
            assertNotNull(tree);
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testTimeDurationParsing() {
        String input = "directive :10s";
        try {
            CharStream stream = CharStreams.fromString(input);
            DirectivesLexer lexer = new DirectivesLexer(stream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            DirectivesParser parser = new DirectivesParser(tokens);
            parser.setErrorHandler(new org.antlr.v4.runtime.BailErrorStrategy()); // Handle errors
            ParseTree tree = parser.statements(); // Assuming 'statements' is the entry point

            // Assert that parsing was successful
            assertNotNull(tree);
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testInvalidInput() {
        String input = "directive :invalid_input"; // Invalid input

        try {
            CharStream stream = CharStreams.fromString(input);
            DirectivesLexer lexer = new DirectivesLexer(stream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            DirectivesParser parser = new DirectivesParser(tokens);
            parser.setErrorHandler(new org.antlr.v4.runtime.BailErrorStrategy()); // Handle errors
            parser.statements(); // Assuming 'statements' is the entry point

            fail("Expected an exception for invalid input");
        } catch (Exception e) {
            // Expected exception for invalid input
        }
    }
}
