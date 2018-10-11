package com.asu.Process;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.asu.UI.ErrorScreen;
import com.asu.UI.Paths;

public class Processor {

	private Map<String, Integer> durations;
	private Map<String, Boolean> isVisited;
	private Map<String, List<String>> nextJob;
	Set<String> startNodes;
	StringBuffer sb;

	public Processor(Dependency dependency) {

		durations = dependency.getDurations();
		isVisited = dependency.getIsVisited();
		nextJob = dependency.getNextJob();
		startNodes = dependency.getStartNodes();
		sb= new StringBuffer();
	}

	public String process() throws Exception {
		int totalDuration = 0;
		List<String> nextJobs = null;
		
		List<String> path = new ArrayList<>();
		for (String startNode : startNodes) {

			totalDuration = 0;
			totalDuration = totalDuration + durations.get(startNode);
			nextJobs = nextJob.get(startNode);
			if (null != nextJobs) {
				if (nextJobs.size() == 1 && isVisited.containsKey(nextJobs.get(0))) {
					ErrorScreen error = new ErrorScreen("Loop found");
					error.setVisible(true);
				} else {
					
						totalDuration = findPathDuration(startNode, path, totalDuration);
					
					
				}
			}

		}
		Paths paths =new Paths(sb.toString());
		paths.setVisible(true);
		sb.delete(0, sb.length());
		return null;
	}

	public int findPathDuration(String startNode, List<String> path, int totalDuration) throws Exception {

		if((nextJob.get(startNode).size()==1 && path.contains(nextJob.get(startNode).get(0))) || nextJob.get(startNode).get(0).equals(startNode))
				{
			          throw new Exception ("Loop found");
				}
		
		if(null==nextJob.get(startNode) || nextJob.get(startNode).size()==0 )
		{
			path.add(startNode);
			printStore(path);
			path.remove(startNode);
			return 0;
		}else {
			for(int i=0;i<nextJob.get(startNode).size();i++)
			{
				path.add(startNode);
				findPathDuration(nextJob.get(startNode).get(i), path, totalDuration);
				path.remove(startNode);
				
			}
			
			
		}
		return 0;
	}
	
	
	public void printStore(List<String> path)
	{
		int totalDuration =0;
		
		for(int i=0;i<path.size();i++)
		{
			totalDuration =totalDuration+durations.get(path.get(i));
			sb.append(path.get(i));
			if(i!=path.size()-1)
				sb.append("-->");
			
		}
		
		sb.append("      "+"Duration   ").append(totalDuration).append("\n");
	}

}
