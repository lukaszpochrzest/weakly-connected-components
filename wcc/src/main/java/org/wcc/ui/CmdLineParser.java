package org.wcc.ui;

import org.apache.commons.cli.*;

public class CmdLineParser {
    private Options options = new Options();

    private HelpFormatter formatter = new HelpFormatter();

    private CommandLineParser parser = new DefaultParser();

    public CmdLineParser() {
        options.addOption( Option.builder().longOpt("graph")
                .desc("graph definition")
                .hasArg()
                .argName("GRAPH_DEFINITION")
                .build());
    }

    public CommandLine getLine(String[] args) throws ParseException {
        return parser.parse( options, args );
    }

    public void printHelp() {
        formatter.printHelp( "wcc", options );
    }
}
