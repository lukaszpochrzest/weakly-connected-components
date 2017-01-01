package org.wcc.ui.args;

import org.apache.commons.cli.*;

public class CmdLineParser {

    public static final String GRAPH_OPTION = "graph";

    public static final String TEST_OPTION = "test";

    private Options options = new Options();

    private HelpFormatter formatter = new HelpFormatter();

    private CommandLineParser parser = new DefaultParser();

    public CmdLineParser() {
        options.addOption( Option.builder().longOpt(GRAPH_OPTION)
                .desc("graph definition")
                .hasArg()
                .argName("GRAPH_DEFINITION")
                .build());

        options.addOption( Option.builder().longOpt(TEST_OPTION)
                .desc("launches complexity tests")
                .build());
    }

    public CommandLine getLine(String[] args) throws ParseException {
        return parser.parse( options, args );
    }

    public void printHelp() {
        formatter.printHelp( "wcc", options );
    }
}
