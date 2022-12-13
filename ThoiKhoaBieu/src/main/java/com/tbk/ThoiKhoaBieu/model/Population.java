package com.tbk.ThoiKhoaBieu.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import lombok.Data;

@Data
public class Population {

	private List<Individual> population;
	private double populationFitness = -1;

	public Population(int populationSize, Timetable timetable) {
		this.population = new ArrayList<>();

		// Loop over population size
		for (int individualCount = 0; individualCount < populationSize; individualCount++) {

			Individual individual = new Individual(timetable);
			// Add individual to population
			this.population.add(individual);
		}
	}

	public Population(int populationSize) {
		this.population = new ArrayList<>();

		for (int i = 0; i < populationSize; i++) {
			population.add(new Individual());
		}
	}

	public Individual getFittest(int index) {
		Collections.sort(this.population, new Comparator<Individual>() {
			@Override
			public int compare(Individual individual1, Individual individual2) {
				if (individual1.getFitness() < individual2.getFitness())
					return 1;
				else if (individual1.getFitness() > individual2.getFitness())
					return -1;
				return 0;
			}
		});
		return this.population.get(index);
	}

	public void shuffle() {
		Random rnd = new Random();
		for (int i = population.size() - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			Individual a = population.get(index);
			population.set(index, population.get(i));
			population.set(i, a);
		}
	}
}
