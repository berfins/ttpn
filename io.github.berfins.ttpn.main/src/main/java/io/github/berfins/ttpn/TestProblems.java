package io.github.berfins.ttpn;

import io.github.ericmedvet.jgea.core.representation.programsynthesis.InstrumentedProgram;
import io.github.ericmedvet.jgea.core.representation.programsynthesis.ProgramExecutionException;
import io.github.ericmedvet.jgea.core.representation.programsynthesis.ttpn.*;
import io.github.ericmedvet.jgea.core.representation.programsynthesis.type.Base;
import io.github.ericmedvet.jgea.core.representation.programsynthesis.type.Composed;
import io.github.ericmedvet.jgea.core.representation.programsynthesis.type.TypeException;
import io.github.ericmedvet.jgea.core.representation.tree.numeric.Element;
import io.github.ericmedvet.jgea.experimenter.drawer.TTPNDrawer;
import io.github.ericmedvet.jgea.problem.programsynthesis.ProgramSynthesisProblem;
import io.github.ericmedvet.jnb.core.NamedBuilder;

import java.util.List;
import java.util.Set;

public class TestProblems {

  public static void main(
      String[] args
  ) throws NetworkStructureException, ProgramExecutionException, NoSuchMethodException, TypeException {
    Network rIntSumGoodNetwork = new Network(
        List.of(
            Gate.input(Base.REAL),
            Gate.input(Base.REAL),
            Gates.rPMathOperator(Element.Operator.ADDITION),
            Gates.rToI(),
            Gate.output(Base.INT)
        ),
        Set.of(
            Wire.of(0, 0, 2, 0),
            Wire.of(1, 0, 2, 1),
            Wire.of(2, 0, 3, 0),
            Wire.of(3, 0, 4, 0)

        )
    );

    Network biLongestStringGoodNetwork = new Network(
        List.of(
            Gate.input(Base.STRING),
            Gate.input(Base.STRING),
            Gates.sSplitter(),
            Gates.sSplitter(),
            Gates.length(),
            Gates.length(),
            Gates.iBefore(),
            Gates.select(),
            Gate.output(Base.STRING)
        ),
        Set.of(
            Wire.of(0, 0, 2, 0),
            Wire.of(1, 0, 3, 0),
            Wire.of(2, 0, 4, 0),
            Wire.of(3, 0, 5, 0),
            Wire.of(4, 0, 6, 0),
            Wire.of(5, 0, 6, 1),
            Wire.of(0, 0, 7, 1),
            Wire.of(1, 0, 7, 0),
            Wire.of(6, 0, 7, 2),
            Wire.of(7, 0, 8, 0)
        )
    );

    Network iArraySumGoodNetwork = new Network(
        List.of(
            Gate.input(Composed.sequence(Base.INT)),
            Gates.splitter(),
            Gates.iSPSum(),
            Gate.output(Base.INT)
        ),
        Set.of(
            Wire.of(0, 0, 1, 0),
            Wire.of(1, 0, 2, 0),
            Wire.of(2, 0, 3, 0),
            Wire.of(2, 0, 2, 1)
        )
    );

    Network iBiMaxGoodNetwork = new Network(
        List.of(
            Gate.input(Base.INT),
            Gate.input(Base.INT),
            Gate.output(Base.INT),
            Gates.iBefore(),
            Gates.select()
        ),
        Set.of(
            Wire.of(0, 0, 3, 0),
            Wire.of(1, 0, 3, 1),
            Wire.of(0, 0, 4, 1),
            Wire.of(1, 0, 4, 0),
            Wire.of(3, 0, 4, 2),
            Wire.of(4, 0, 2, 0)
        )
    );

    Network iTriMaxGoodNetwork = new Network(
        List.of(
            Gate.input(Base.INT),
            Gate.input(Base.INT),
            Gate.input(Base.INT),
            Gates.iBefore(),
            Gates.select(),
            Gates.iBefore(),
            Gates.select(),
            Gate.output(Base.INT)
        ),
        Set.of(
            Wire.of(0, 0, 3, 0),
            Wire.of(1, 0, 3, 1),
            Wire.of(0, 0, 4, 1),
            Wire.of(1, 0, 4, 0),
            Wire.of(3, 0, 4, 2),

            Wire.of(4, 0, 5, 0),
            Wire.of(2, 0, 5, 1),
            Wire.of(4, 0, 6, 1),
            Wire.of(2, 0, 6, 0),
            Wire.of(5, 0, 6, 2),
            Wire.of(6, 0, 7, 0)
        )
    );

    Network vScProductGoodNetwork = new Network(
        List.of(
            Gate.input(Composed.sequence(Base.REAL)),
            Gate.input(Base.REAL),
            Gates.length(),
            Gates.repeater(),
            Gates.splitter(),
            Gates.rPMathOperator(Element.Operator.MULTIPLICATION),
            Gates.sPSequencer(),
            Gate.output(Composed.sequence(Base.REAL))
        ),
        Set.of(
            Wire.of(1, 0, 3, 0),
            Wire.of(0, 0, 2, 0),
            Wire.of(0, 0, 4, 0),
            Wire.of(2, 0, 3, 1),
            Wire.of(3, 0, 5, 0),
            Wire.of(4, 0, 5, 1),
            Wire.of(5, 0, 6, 0),
            Wire.of(6, 0, 6, 1),
            Wire.of(6, 0, 7, 0)
        )
    );

    Network sLengtherGoodNetwork = new Network(
        List.of(
            Gate.input(Composed.sequence(Base.STRING)),
            Gates.splitter(),
            Gates.sSplitter(),
            Gates.length(),
            Gates.pairer(),
            Gates.sPSequencer(),
            Gate.output(Composed.sequence(Composed.tuple(List.of(Base.STRING, Base.INT))))
        ),
        Set.of(
            Wire.of(0, 0, 1, 0),
            Wire.of(1, 0, 2, 0),
            Wire.of(1, 0, 4, 0),
            Wire.of(2, 0, 3, 0),
            Wire.of(3, 0, 4, 1),
            Wire.of(4, 0, 5, 0),
            Wire.of(5, 0, 5, 1),
            Wire.of(5, 0, 6, 0)

        )
    );


    Network triLongestStringGoodNetwork = new Network(
        List.of(
            Gate.input(Base.STRING),
            Gate.input(Base.STRING),
            Gate.input(Base.STRING),
            Gates.sSplitter(),
            Gates.sSplitter(),
            Gates.sSplitter(),
            Gates.length(),
            Gates.length(),
            Gates.length(),
            Gates.iBefore(),
            Gates.select(),
            Gates.sSplitter(),
            Gates.length(),
            Gates.iBefore(),
            Gates.select(),
            Gate.output(Base.STRING)
        ),
        Set.of(
            Wire.of(0, 0, 3, 0),
            Wire.of(0, 0, 10, 1),
            Wire.of(1, 0, 4, 0),
            Wire.of(1, 0, 10, 0),
            Wire.of(3, 0, 6, 0),
            Wire.of(4, 0, 7, 0),
            Wire.of(6, 0, 9, 0),
            Wire.of(7, 0, 9, 1),
            Wire.of(9, 0, 10, 2),
            Wire.of(10, 0, 11, 0),
            Wire.of(10, 0, 14, 1),
            Wire.of(2, 0, 5, 0),
            Wire.of(5, 0, 8, 0),
            Wire.of(8, 0, 13, 1),
            Wire.of(2, 0, 14, 0),
            Wire.of(11, 0, 12, 0),
            Wire.of(12, 0, 13, 0),
            Wire.of(13, 0, 14, 2),
            Wire.of(14, 0, 15, 0)
        )
    );

    Network vProductGoodNetwork = new Network(
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

    Network rIntSumBiggerNetwork = new Network(
        List.of(
            Gate.input(Base.REAL),
            Gate.input(Base.REAL),
            Gates.rSPMult(),
            Gates.noop(),
            Gates.rPMathOperator(Element.Operator.ADDITION),
            Gates.rToI(),
            Gates.iConst(5),
            Gates.repeater(),
            Gates.noop(),
            Gates.sPSequencer(),
            Gates.splitter(),
            Gate.output(Base.INT)
        ),
        Set.of(
            Wire.of(0, 0, 2, 0),
            Wire.of(2, 0, 2, 1),
            Wire.of(1, 0, 3, 0),
            Wire.of(3, 0, 4, 1),
            Wire.of(2, 0, 4, 0),
            Wire.of(4, 0, 5, 0),
            Wire.of(5, 0, 7, 0),
            Wire.of(3, 0, 6, 0),
            Wire.of(6, 0, 7, 1),
            Wire.of(7, 0, 8, 0),
            Wire.of(8, 0, 9, 0),
            Wire.of(9, 0, 9, 1),
            Wire.of(9, 0, 10, 0),
            Wire.of(10, 0, 11, 0)

        )
    );


    Network vScProductBiggerNetwork = new Network(
        List.of(
            Gate.input(Composed.sequence(Base.REAL)),
            Gate.input(Base.REAL),
            Gates.length(),
            Gates.repeater(),
            Gates.queuer(),
            Gates.noop(),
            Gates.repeater(),
            Gates.splitter(),
            Gates.rPMathOperator(Element.Operator.MULTIPLICATION),
            Gates.sPSequencer(),
            Gates.noop(),
            Gate.output(Composed.sequence(Base.REAL))
        ),
        Set.of(
            Wire.of(1, 0, 6, 0),
            Wire.of(0, 0, 2, 0),
            Wire.of(0, 0, 3, 0),
            Wire.of(2, 0, 3, 1),
            Wire.of(3, 0, 7, 0),
            Wire.of(2, 0, 4, 0),
            Wire.of(2, 0, 4, 1),
            Wire.of(4, 0, 5, 0),
            Wire.of(5, 0, 6, 1),
            Wire.of(6, 0, 8, 0),
            Wire.of(7, 0, 8, 1),
            Wire.of(8, 0, 9, 0),
            Wire.of(9, 0, 9, 1),
            Wire.of(9, 0, 10, 0),
            Wire.of(10, 0, 11, 0)
        )
    );


    Network iArraySumBiggerNetwork = new Network(
        List.of(
            Gate.input(Composed.sequence(Base.INT)),
            Gates.splitter(),
            Gates.iSPSum(),
            Gate.output(Base.INT),
            Gates.noop(),
            Gates.iSPSum(),
            Gates.noop()
        ),
        Set.of(
            Wire.of(0, 0, 4, 0),
            Wire.of(4, 0, 1, 0),
            Wire.of(1, 0, 2, 0),
            Wire.of(1, 0, 5, 0),
            Wire.of(2, 0, 6, 0),
            Wire.of(2, 0, 5, 1),
            Wire.of(5, 0, 2, 1),
            Wire.of(6, 0, 3, 0)
        )
    );


    Network iTriMaxBiggerNetwork = new Network(
        List.of(
            Gate.input(Base.INT),
            Gate.input(Base.INT),
            Gate.input(Base.INT),
            Gates.iBefore(),
            Gates.select(),
            Gates.iBefore(),
            Gates.select(),
            Gate.output(Base.INT),
            Gates.noop(),
            Gates.noop(),
            Gates.noop(),
            Gates.noop()
        ),
        Set.of(
            Wire.of(0, 0, 8, 0),
            Wire.of(8, 0, 3, 0),

            Wire.of(1, 0, 3, 1),
            Wire.of(0, 0, 4, 1),
            Wire.of(1, 0, 9, 0),
            Wire.of(9, 0, 4, 0),

            Wire.of(3, 0, 4, 2),

            Wire.of(4, 0, 5, 0),
            Wire.of(2, 0, 5, 1),
            Wire.of(4, 0, 6, 1),
            Wire.of(2, 0, 10, 0),
            Wire.of(10, 0, 6, 0),

            Wire.of(5, 0, 6, 2),
            Wire.of(6, 0, 11, 0),

            Wire.of(11, 0, 7, 0)
        )
    );


    Network triLongestStringBiggerNetwork = new Network(
        List.of(
            Gate.input(Base.STRING),
            Gate.input(Base.STRING),
            Gate.input(Base.STRING),
            Gates.sSplitter(),
            Gates.sSplitter(),
            Gates.sSplitter(),
            Gates.length(),
            Gates.length(),
            Gates.length(),
            Gates.iBefore(),
            Gates.select(),
            Gates.sSplitter(),
            Gates.length(),
            Gates.iBefore(),
            Gates.select(),
            Gate.output(Base.STRING),
            Gates.noop(),
            Gates.noop(),
            Gates.noop(),
            Gates.noop(),
            Gates.noop()
        ),
        Set.of(
            Wire.of(0, 0, 16, 0),
            Wire.of(16, 0, 3, 0),

            Wire.of(0, 0, 10, 1),
            Wire.of(1, 0, 4, 0),
            Wire.of(1, 0, 10, 0),
            Wire.of(3, 0, 6, 0),
            Wire.of(4, 0, 7, 0),
            Wire.of(6, 0, 9, 0),
            Wire.of(7, 0, 18, 0),
            Wire.of(18, 0, 9, 1),

            Wire.of(9, 0, 10, 2),
            Wire.of(10, 0, 17, 0),
            Wire.of(17, 0, 11, 0),

            Wire.of(10, 0, 14, 1),
            Wire.of(2, 0, 5, 0),
            Wire.of(5, 0, 19, 0),
            Wire.of(19, 0, 8, 0),

            Wire.of(8, 0, 13, 1),
            Wire.of(2, 0, 14, 0),
            Wire.of(11, 0, 12, 0),
            Wire.of(12, 0, 13, 0),
            Wire.of(13, 0, 14, 2),

            Wire.of(14, 0, 20, 0),
            Wire.of(20, 0, 15, 0)
        )
    );

    Network sLengtherBiggerNetwork = new Network(
        List.of(
            Gate.input(Composed.sequence(Base.STRING)),
            Gates.noop(),
            Gates.splitter(),
            Gates.sSplitter(),
            Gates.noop(),
            Gates.length(),
            Gates.pairer(),
            Gates.sPSequencer(),
            Gates.noop(),
            Gate.output(Composed.sequence(Composed.tuple(List.of(Base.STRING, Base.INT))))
        ),
        Set.of(
            Wire.of(0, 0, 1, 0),
            Wire.of(1, 0, 2, 0),
            Wire.of(2, 0, 3, 0),
            Wire.of(2, 0, 6, 0),
            Wire.of(3, 0, 4, 0),
            Wire.of(4, 0, 5, 0),
            Wire.of(5, 0, 6, 1),
            Wire.of(6, 0, 7, 0),
            Wire.of(7, 0, 7, 1),
            Wire.of(7, 0, 8, 0),
            Wire.of(8, 0, 9, 0)

        )
    );


    Network biLongestStringBiggerNetwork = new Network(
        List.of(
            Gate.input(Base.STRING),
            Gate.input(Base.STRING),
            Gates.sSplitter(),
            Gates.length(),
            Gates.iBefore(),
            Gates.noop(),
            Gates.sSplitter(),
            Gates.length(),
            Gates.bOr(),
            Gates.select(),
            Gates.noop(),
            Gate.output(Base.STRING),
            Gates.repeater()
        ),
        Set.of(
            Wire.of(0, 0, 9, 1),
            Wire.of(0, 0, 2, 0),
            Wire.of(2, 0, 3, 0),
            Wire.of(3, 0, 4, 0),
            Wire.of(4, 0, 8, 0),
            Wire.of(4, 0, 8, 1),
            Wire.of(8, 0, 9, 2),
            Wire.of(9, 0, 10, 0),
            Wire.of(10, 0, 11, 0),
            Wire.of(1, 0, 5, 0),
            Wire.of(5, 0, 6, 0),
            Wire.of(6, 0, 7, 0),
            Wire.of(7, 0, 4, 1),
            Wire.of(1, 0, 12, 0),
            Wire.of(7, 0, 12, 1),
            Wire.of(12, 0, 9, 0)

        )
    );


    Network iBiMaxBiggerNetwork = new Network(
        List.of(
            Gate.input(Base.INT),
            Gate.input(Base.INT),
            Gates.noop(),
            Gates.iBefore(),
            Gates.noop(),
            Gates.select(),
            Gates.repeater(),
            Gates.noop(),
            Gate.output(Base.INT)
        ),
        Set.of(
            Wire.of(0, 0, 3, 0),
            Wire.of(0, 0, 4, 0),
            Wire.of(4, 0, 5, 1),
            Wire.of(1, 0, 2, 0),
            Wire.of(1, 0, 5, 0),
            Wire.of(2, 0, 3, 1),
            Wire.of(3, 0, 5, 2),
            Wire.of(5, 0, 6, 0),
            Wire.of(5, 0, 6, 1),
            Wire.of(6, 0, 7, 0),
            Wire.of(7, 0, 8, 0)
        )
    );

    Network remainderGoodNetwork = new Network(
        List.of(
            Gate.input(Base.INT),
            Gate.input(Base.INT),
            Gates.iToR(),
            Gates.iToR(),
            Gates.rPMathOperator(Element.Operator.DIVISION),
            Gates.rToI(),
            Gates.iPMathOperator(Element.Operator.MULTIPLICATION),
            Gates.iPMathOperator(Element.Operator.SUBTRACTION),
            Gate.output(Base.INT)
        ),
        Set.of(
            Wire.of(0, 0, 2, 0),
            Wire.of(0, 0, 7, 0),
            Wire.of(1, 0, 3, 0),
            Wire.of(1, 0, 6, 1),
            Wire.of(2, 0, 4, 0),
            Wire.of(3, 0, 4, 1),
            Wire.of(4, 0, 5, 0),
            Wire.of(5, 0, 6, 0),
            Wire.of(6, 0, 7, 1),
            Wire.of(7, 0, 8, 0)

        )
    );

    Network remainderBiggerNetwork = new Network(
        List.of(
            Gate.input(Base.INT),
            Gate.input(Base.INT),
            Gates.iToR(),
            Gates.iToR(),
            Gates.rPMathOperator(Element.Operator.DIVISION),
            Gates.rToI(),
            Gates.iPMathOperator(Element.Operator.MULTIPLICATION),
            Gates.iPMathOperator(Element.Operator.SUBTRACTION),
            Gate.output(Base.INT),
            Gates.rSPMult()
        ),
        Set.of(
            Wire.of(0, 0, 2, 0),
            Wire.of(0, 0, 7, 0),
            Wire.of(1, 0, 3, 0),
            Wire.of(1, 0, 6, 1),
            Wire.of(2, 0, 9, 0),
            Wire.of(9, 0, 4, 0),
            Wire.of(9, 0, 9, 1),
            Wire.of(3, 0, 4, 1),
            Wire.of(4, 0, 5, 0),
            Wire.of(5, 0, 6, 0),
            Wire.of(6, 0, 7, 1),
            Wire.of(7, 0, 8, 0)

        )
    );

    Network vProductBiggerNetwork = new Network(
        List.of(
            Gate.input(Composed.sequence(Base.REAL)),
            Gate.input(Composed.sequence(Base.REAL)),
            Gates.splitter(),
            Gates.splitter(),
            Gates.queuer(),
            Gates.rSMult(),
            Gates.rSPSum(),
            Gate.output(Base.REAL),
            Gates.noop(),
            Gates.noop(),
            Gates.noop()
        ),
        Set.of(
            Wire.of(2, 0, 4, 0),
            Wire.of(3, 0, 4, 1),
            Wire.of(4, 0, 5, 0),
            Wire.of(5, 0, 6, 0),
            Wire.of(6, 0, 6, 1),
            Wire.of(0, 0, 8, 0),
            Wire.of(8, 0, 2, 0),
            Wire.of(1, 0, 9, 0),
            Wire.of(9, 0, 3, 0),
            Wire.of(6, 0, 10, 0),
            Wire.of(10, 0, 7, 0)


        )
    );

    NamedBuilder<?> nb = NamedBuilder.fromDiscovery();
    ProgramSynthesisProblem rIntSumpsb = (ProgramSynthesisProblem) nb.build(
        "ea.p.ps.synthetic(name = \"rIntSum\"; metrics = [fail_rate; avg_raw_dissimilarity; exception_error_rate; profile_avg_steps; profile_avg_tot_size])"
    );
    ProgramSynthesisProblem biLongestStringpsb = (ProgramSynthesisProblem) nb.build(
        "ea.p.ps.synthetic(name = \"biLongestString\"; metrics = [fail_rate; avg_raw_dissimilarity; exception_error_rate; profile_avg_steps; profile_avg_tot_size])"
    );
    ProgramSynthesisProblem iArraySumpsb = (ProgramSynthesisProblem) nb.build(
        "ea.p.ps.synthetic(name = \"iArraySum\"; metrics = [fail_rate; avg_raw_dissimilarity; exception_error_rate; profile_avg_steps; profile_avg_tot_size])"
    );
    ProgramSynthesisProblem iBiMaxpsb = (ProgramSynthesisProblem) nb.build(
        "ea.p.ps.synthetic(name = \"iBiMax\"; metrics = [fail_rate; avg_raw_dissimilarity; exception_error_rate; profile_avg_steps; profile_avg_tot_size])"
    );
    ProgramSynthesisProblem iTriMaxpsb = (ProgramSynthesisProblem) nb.build(
        "ea.p.ps.synthetic(name = \"iTriMax\"; metrics = [fail_rate; avg_raw_dissimilarity; exception_error_rate; profile_avg_steps; profile_avg_tot_size])"
    );
    ProgramSynthesisProblem vScProductpsb = (ProgramSynthesisProblem) nb.build(
        "ea.p.ps.synthetic(name = \"vScProduct\"; metrics = [fail_rate; avg_raw_dissimilarity; exception_error_rate; profile_avg_steps; profile_avg_tot_size])"
    );
    ProgramSynthesisProblem triLongestStringpsb = (ProgramSynthesisProblem) nb.build(
        "ea.p.ps.synthetic(name = \"triLongestString\"; metrics = [fail_rate; avg_raw_dissimilarity; exception_error_rate; profile_avg_steps; profile_avg_tot_size])"
    );
    ProgramSynthesisProblem vProductpsb = (ProgramSynthesisProblem) nb.build(
        "ea.p.ps.synthetic(name = \"vProduct\"; metrics = [smooth_fail_rate; avg_raw_dissimilarity; exception_error_rate; profile_avg_steps; profile_avg_tot_size])"
    );

    ProgramSynthesisProblem sLengtherpsb = (ProgramSynthesisProblem) nb.build(
        "ea.p.ps.synthetic(name = \"sLengther\"; metrics = [fail_rate; exception_error_rate; profile_avg_steps; profile_avg_tot_size])"
    );
    ProgramSynthesisProblem remainderpsb = (ProgramSynthesisProblem) nb.build(
        "ea.p.ps.synthetic(name = \"remainder\"; metrics = [fail_rate; avg_raw_dissimilarity; exception_error_rate; profile_avg_steps; profile_avg_tot_size])"
    );

    // CHOSEN NETWORK AND PROGRAM SYNTHESIS PROBLEM
    Network chosenNetwork = triLongestStringGoodNetwork;
    ProgramSynthesisProblem psb = triLongestStringpsb;

    TTPNDrawer drawer = new TTPNDrawer(TTPNDrawer.Configuration.DEFAULT);
    drawer.show(chosenNetwork);

    Runner runner = new Runner(100, 1000, 1000, 100, false);

    System.out.println("Network:");

    psb.caseProvider()
        .stream()
        .forEach(
            e -> {
              InstrumentedProgram.InstrumentedOutcome outcome = runner.run(chosenNetwork, e.input());
              System.out.printf(
                  "in=%s\tactualOut=%s\tpredOut=%s exc=%s%n",
                  e.input(),
                  e.output().outputs(),
                  outcome.outputs(),
                  outcome.exception()
              );
            }
        );
    System.out.println(psb.qualityFunction().apply(runner.asInstrumentedProgram(chosenNetwork)));
  }
}