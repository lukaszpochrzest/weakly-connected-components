package org.wcc.ui;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import org.wcc.complexity.ComplexityTest;
import org.wcc.ui.args.CmdLineParser;
import org.wcc.ui.args.Mode;
import org.wcc.ui.args.ModeResolver;
import org.wcc.ui.exception.GraphFileReadException;
import org.wcc.ui.exception.InvalidGraphFileFormatException;
import org.wcc.ui.exception.MoreThanOneOptionChosenException;
import org.wcc.ui.exception.NoOptionChosenException;


public class Main {

    public static void main(String[] args) {
        CmdLineParser cmdLineParser = new CmdLineParser();
        ModeResolver modeResolver = new ModeResolver();

        try {
            CommandLine line = cmdLineParser.getLine(args);
            Mode mode = modeResolver.resolve(line);

            if(Mode.NORMAL.equals(mode)) {

                WCCExecutor.execute(line.getOptionValue(CmdLineParser.GRAPH_OPTION));

            } else if(Mode.TEST.equals(mode)) {

                ComplexityTest.test();

            } else {
                throw new UnsupportedOperationException();
            }


        } catch (ParseException e) {
            System.out.println(e.getMessage());
            cmdLineParser.printHelp();
            System.exit(1);
        } catch (MoreThanOneOptionChosenException e) {
            System.out.println("Choose only one of the options");
            cmdLineParser.printHelp();
            System.exit(2);
        } catch (NoOptionChosenException e) {
            System.out.println("Choose the option");
            cmdLineParser.printHelp();
            System.exit(3);
        } catch (GraphFileReadException e) {
            System.out.println("No such graph file. Check usage.");
            cmdLineParser.printHelp();
            System.exit(4);
        } catch (InvalidGraphFileFormatException e) {
            System.out.println("Graph file format is invalid.");
            System.exit(5);
        }

    }

}
