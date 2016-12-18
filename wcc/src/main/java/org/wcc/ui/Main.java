package org.wcc.ui;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import org.wcc.algorithm.WCCUtils;
import org.wcc.data.WeaklyConnectedComponent;
import org.wcc.ui.config.GraphConfig;
import org.wcc.ui.config.GraphConfigParser;
import org.wcc.ui.exception.GraphFileReadException;
import org.wcc.ui.exception.InvalidGraphFileFormatException;
import org.wcc.ui.exception.NoGraphOptionException;
import org.wcc.ui.io.FileReader;

import java.util.List;


public class Main {

    public static void main(String[] args) {

        CmdLineParser cmdLineParser = new CmdLineParser();
        try {
            CommandLine line = cmdLineParser.getLine(args);

            if (!line.hasOption("graph")) {
                throw new NoGraphOptionException();
            } else {
                String graphConfigFile = FileReader.read(line.getOptionValue("graph"));
                GraphConfig graphConfig = GraphConfigParser.toObject(graphConfigFile);
                List<WeaklyConnectedComponent> wccs = WCCUtils.computeWCC(graphConfig.convert());
                System.out.println("Found " + wccs.size() + " weakly connected components:\n");
                wccs.forEach(System.out::println);
            }

        } catch (ParseException e) {
            System.out.println(e.getMessage());
            cmdLineParser.printHelp();
            System.exit(1);
        } catch (NoGraphOptionException e) {
            System.out.println("A graph option is necessary. Check usage.");
            cmdLineParser.printHelp();
            System.exit(2);
        } catch (GraphFileReadException e) {
            System.out.println("No such graph file. Check usage.");
            cmdLineParser.printHelp();
            System.exit(3);
        } catch (InvalidGraphFileFormatException e) {
            System.out.println("Graph file format is invalid.");
            System.exit(4);
        }

    }

}
