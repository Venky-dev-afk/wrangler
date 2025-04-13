package io.cdap.directives.aggregates;

import java.util.List;

import org.apache.xmlbeans.XmlCursor.TokenType;

import io.cdap.cdap.api.annotation.Description;
import io.cdap.cdap.api.annotation.Name;
import io.cdap.cdap.api.annotation.Plugin;
import io.cdap.wrangler.api.Arguments;
import io.cdap.wrangler.api.Directive;
import io.cdap.wrangler.api.DirectiveExecutionException;
import io.cdap.wrangler.api.DirectiveParseException;
import io.cdap.wrangler.api.ErrorRowException;
import io.cdap.wrangler.api.ExecutorContext;
import io.cdap.wrangler.api.ReportErrorAndProceed;
import io.cdap.wrangler.api.Row;
import io.cdap.wrangler.api.parser.UsageDefinition;

@Plugin(type = Directive.PLUGIN_TYPE)
@Name("aggregate-stats")
@Description("Aggregates byte size and time duration columns.")
public class AggregateStatsDirective implements Directive {
    private String byteColumn;
    private String timeColumn;
    private String totalByteColumn;
    private String totalTimeColumn;

    @Override
    public UsageDefinition define() {
        return UsageDefinition.builder(byteColumn)
            .define("byteColumn", TokenType.COLUMN_NAME)
            .define("timeColumn", TokenType.COLUMN_NAME)
            .define("totalByteColumn", TokenType.COLUMN_NAME)
            .define("totalTimeColumn", TokenType.COLUMN_NAME)
            .build();
    }

    @Override
    public void initialize(Arguments args) {
        byteColumn = args.value("byteColumn");
        timeColumn = args.value("timeColumn");
        totalByteColumn = args.value("totalByteColumn");
        totalTimeColumn = args.value("totalTimeColumn");
    }

    @Override
    public Row execute(Row row, ExecutorContext context) throws DirectiveExecutionException {
        // Implement aggregation logic here.
        return row;
    }

    @Override
    public void destroy() {}

    @Override
    public void initialize(Arguments args) throws DirectiveParseException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    }

    @Override
    public List<Row> execute(List<Row> rows, ExecutorContext context)
            throws DirectiveExecutionException, ErrorRowException, ReportErrorAndProceed {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
}
