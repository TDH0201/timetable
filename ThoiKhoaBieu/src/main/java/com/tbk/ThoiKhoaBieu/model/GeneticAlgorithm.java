package com.tbk.ThoiKhoaBieu.model;

import java.util.Random;

import lombok.Data;

@Data
public class GeneticAlgorithm {

	private int populationSize;
	private double mutationRate;
	private double crossoverRate;
	private int elitismCount;
	protected int tournamentSize;

	public GeneticAlgorithm(int populationSize, double mutationRate, double crossoverRate, int elitismCount,
			int tournamentSize) {

		this.populationSize = populationSize;
		this.mutationRate = mutationRate;
		this.crossoverRate = crossoverRate;
		this.elitismCount = elitismCount;
		this.tournamentSize = tournamentSize;
	}

	public Population initPopulation(Timetable timetable) {
		Population population = new Population(this.populationSize, timetable);
		return population;
	}

	public void evalPopulation(Population population, Timetable timetable) {
		double populationFitness = 0;

		// Loop over population evaluating individuals and summing population
		// fitness
		for (Individual individual : population.getPopulation()) {
			populationFitness += this.calcFitness(individual, timetable);
		}
		
		//System.out.println("populationFitness: "+populationFitness);
		population.setPopulationFitness(populationFitness);
	}

	public double calcFitness(Individual individual, Timetable timetable) {

		Timetable threadTimetable = new Timetable(timetable);
		threadTimetable.createClasses(individual);

		int clashes = threadTimetable.calcClashes();
		double fitness = 1 / (double) (clashes + 1);

		//System.out.println("fitness: "+fitness);
		individual.setFitness(fitness);

		return fitness;
	}

	public boolean isTerminationConditionMet(int generationsCount, int maxGenerations) {
		return (generationsCount < maxGenerations);
	}

	public boolean isTerminationConditionMet(Population population) {
		return population.getFittest(0).getFitness() == 1.0;
	}

	public Population crossoverPopulation(Population population) {

		Population newPopulation = new Population(population.getPopulation().size());
		for (int populationIndex = 0; populationIndex < population.getPopulation().size(); populationIndex++) {

			Individual parent1 = population.getFittest(populationIndex);

			if (this.crossoverRate > Math.random() && populationIndex >= this.elitismCount) {

				Individual children = new Individual(parent1.getChromosome().size());
				Individual parent2 = selectParent(population);

				for (int geneIndex = 0; geneIndex < parent1.getChromosome().size(); geneIndex++) {
					if (0.5 > Math.random()) {
						children.getChromosome().set(geneIndex, parent1.getChromosome().get(geneIndex));
					} else {
						children.getChromosome().set(geneIndex, parent2.getChromosome().get(geneIndex));
					}
				}

				newPopulation.getPopulation().set(populationIndex, children);
			} else {
				newPopulation.getPopulation().set(populationIndex, parent1);
			}
		}
		return newPopulation;
	}

	private Individual selectParent(Population population) {
		Population populationClone = new Population(population.getPopulation().size());

		population.shuffle();
		for (int i = 0; i < this.tournamentSize; i++) {
			Individual tournamentIndividual = population.getPopulation().get(i);
			populationClone.getPopulation().set(i, tournamentIndividual);
		}

		return populationClone.getFittest(0);
	}

	public Population mutatePopulation(Population population, Timetable timetable) {

		Population newPopulation = new Population(population.getPopulation().size());

		for (int populationIndex = 0; populationIndex < population.getPopulation().size(); populationIndex++) {
			Individual individual = population.getFittest(populationIndex);

			Individual randomIndividual = new Individual(timetable);

			for (int geneIndex = 0; geneIndex < individual.getChromosome().size(); geneIndex++) {

				if (populationIndex > this.elitismCount) {
					if (this.mutationRate > Math.random()) {
						individual.getChromosome().set(geneIndex, randomIndividual.getChromosome().get(geneIndex));
					}
				}
			}

			newPopulation.getPopulation().set(populationIndex, individual);
		}

		return newPopulation;
	}

}
