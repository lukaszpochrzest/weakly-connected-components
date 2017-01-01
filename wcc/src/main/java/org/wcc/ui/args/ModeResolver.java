package org.wcc.ui.args;

import org.apache.commons.cli.CommandLine;
import org.wcc.ui.exception.MoreThanOneOptionChosenException;
import org.wcc.ui.exception.NoOptionChosenException;

public class ModeResolver {

    public Mode resolve(CommandLine commandLine) {
        return resolve(commandLine.hasOption(CmdLineParser.GRAPH_OPTION), commandLine.hasOption(CmdLineParser.TEST_OPTION));
    }

    private Mode resolve(boolean graph, boolean test) {
        if(test && graph) {
            throw new MoreThanOneOptionChosenException();
        } else if(test && !graph) {
            return Mode.TEST;
        } else if(!test && graph) {
            return Mode.NORMAL;
        } else {    //  !test && !graph
            throw new NoOptionChosenException();
        }
    }


}
