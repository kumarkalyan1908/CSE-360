package com.asu.Process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dependency {
	
	private Map<String,Integer> durations ;
	private Map<String,Boolean> isVisited ;
	private Map<String, List<String>> nextJob ;
	Set<String> startNodes ;
	
	public Map<String, Integer> getDurations() {
		return durations;
	}

	public void setDurations(Map<String, Integer> durations) {
		this.durations = durations;
	}

	public Map<String, Boolean> getIsVisited() {
		return isVisited;
	}

	public void setIsVisited(Map<String, Boolean> isVisited) {
		this.isVisited = isVisited;
	}

	public Map<String, List<String>> getNextJob() {
		return nextJob;
	}

	public void setNextJob(Map<String, List<String>> nextJob) {
		this.nextJob = nextJob;
	}

	public Set<String> getStartNodes() {
		return startNodes;
	}

	public void setStartNodes(Set<String> startNodes) {
		this.startNodes = startNodes;
	}

	
	
	public Dependency() {
		super();
		this.durations =  new HashMap<>();
		this.isVisited = new HashMap<>();
		this.nextJob = new HashMap<>();
		this.startNodes = new HashSet<>();
	}
	
	
	public void clearAll() {
		
		durations.clear();
		isVisited.clear();
		nextJob.clear();
		startNodes.clear();
	}
	
	
	public void putInDurations(String activityName, int duration)
	{
		
			durations.put(activityName, duration);
		
	}
	
	public void putInNextJobs(String activityName, String dependency)
	{
		
		if(nextJob.containsKey(dependency))
		{
			nextJob.get(dependency).add(activityName);
			
		}else {
			
			List<String> nextJobs = new ArrayList<>();
			nextJobs.add(activityName);
			nextJob.put(dependency, nextJobs);
		}
	}
	
	
	public void putInStartNodes(String activityName)
	{
		startNodes.add(activityName);
	}
	


	
	

}
