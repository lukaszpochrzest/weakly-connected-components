package org.wcc.ui;

import org.wcc.algorithm.WCCUtils;
import org.wcc.algorithm.data.impl.WeaklyConnectedComponentsImpl;
import org.wcc.ui.config.GraphConfig;
import org.wcc.ui.config.GraphConfigParser;
import org.wcc.ui.exception.GraphFileReadException;
import org.wcc.ui.exception.InvalidGraphFileFormatException;
import org.wcc.ui.io.FileReader;

import java.util.List;

public class WCCExecutor {

    public static void execute(String graphFile) throws GraphFileReadException, InvalidGraphFileFormatException {
        // read graph file
        String graphConfigFile = FileReader.read(graphFile);

        // parse grah file
        GraphConfig graphConfig = GraphConfigParser.toObject(graphConfigFile);

        // execute algorithm
        WeaklyConnectedComponentsImpl<Integer> wccs = WCCUtils.computeWCC(graphConfig.convert());

        // print results
        List<String> printedWccs = wccs.printToString();
        printedWccs.forEach(System.out::println);
    }

}
