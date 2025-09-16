package io.github.berfins.ttpn;

import io.github.ericmedvet.jgea.core.representation.programsynthesis.ttpn.*;
import io.github.ericmedvet.jgea.core.representation.programsynthesis.type.Base;
import io.github.ericmedvet.jgea.core.representation.programsynthesis.type.Composed;
import io.github.ericmedvet.jgea.core.representation.programsynthesis.type.TypeException;
import io.github.ericmedvet.jgea.core.representation.tree.numeric.Element;
import io.github.ericmedvet.jgea.experimenter.drawer.TTPNDrawer;
import io.github.ericmedvet.jgea.experimenter.drawer.TTPNOutcomeVideoBuilder;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class ApplyInput {
  public static void main(String[] args) throws NetworkStructureException, TypeException, IOException {
    // Build: vector product network
    Network n = new Network(
        List.of(
            Gate.input(Composed.sequence(Base.REAL)),
            Gate.input(Composed.sequence(Base.REAL)),
            Gates.splitter(),
            Gates.splitter(),
            Gates.rPMathOperator(Element.Operator.MULTIPLICATION),
            Gates.rSPSum(),
            Gate.output(Base.REAL)
        ),
        Set.of(
            Wire.of(0, 0, 2, 0),
            Wire.of(1, 0, 3, 0),
            Wire.of(2, 0, 4, 0),
            Wire.of(3, 0, 4, 1),
            Wire.of(4, 0, 5, 0),
            Wire.of(5, 0, 5, 1),
            Wire.of(5, 0, 6, 0)
        )
    );
    // Save an image of the network
    TTPNDrawer drawer = new TTPNDrawer(TTPNDrawer.Configuration.DEFAULT);
    //drawer.show(n);
    drawer.save(new File("../ttpn-vProduct.png"), n);

    // Runner
    Runner runner = new Runner(1000, 1000, 1000, 100, false);
    // Run the network with example inputs
    Runner.TTPNInstrumentedOutcome outcome = runner.run(
        n,
        List.of(
            List.of(1.0, 2.0, 3.0),
            List.of(4.0, 5.0, 6.0)
        )
    );
    // Result
    System.out.println(outcome.outputs());
    // Steps
    System.out.println(outcome.profile());


    // Video of the working network is saved
    TTPNOutcomeVideoBuilder videoBuilder = new TTPNOutcomeVideoBuilder(TTPNOutcomeVideoBuilder.Configuration.DEFAULT);
    videoBuilder.save(new File("../ttpn-vProduct.mp4"), outcome);
  }
}

