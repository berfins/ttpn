package io.github.berfins.ttpn;

import io.github.ericmedvet.jgea.experimenter.drawer.Experiments;

public class Main {
  public static void main(String[] args) {
    System.out.println("Hello from TTPN project!");
    System.out.println("Found class: " + Experiments.class.getName());
    System.out.println("Loaded from: " + Experiments.class
        .getProtectionDomain()
        .getCodeSource()
        .getLocation());
  }
}
