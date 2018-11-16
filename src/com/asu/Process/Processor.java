package com.asu.Process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
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
	private Map<Integer,List<String>> mapPath = new HashMap<>();
	StringBuffer sb;
	private List<String> criticalPaths = new ArrayList<>();
	private String pathsFinal;
	private List<String> pathListFinal;
	
	public List<String> getPathlistFinal()
	{
		return pathListFinal;
	}
	public String getPathsFinal()
	{
		return pathsFinal;
	}
	public Map<Integer, List<String>> getMapPath() {
		return mapPath;
	}


	public List<String> getCriticalPaths() {
		return criticalPaths;
	}


	public Map<String, Integer> getPathDuration() {
		return pathDuration;
	}


	private Map<String,Integer> pathDuration = new HashMap<>();
	

	Set<Integer> durationList = new HashSet<>();
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
		
		List<Integer> sortList = new ArrayList<>(durationList);
		Collections.sort(sortList);
		
		List<String> strPath =null;
		//String strPaths [] = null;
		//List<String> listTemp =null;
		criticalPaths = mapPath.get(sortList.get(sortList.size()-1));
		StringBuffer sb1 = new StringBuffer();
		pathListFinal =new ArrayList<>();
		for(int i=sortList.size()-1;i>=0;i--)
		{
			strPath = mapPath.get(sortList.get(i));
			
			for(int j=0;j<strPath.size();j++)
			{
				sb.append(strPath.get(j)).append("      "+"Duration   ").append(sortList.get(i)).append("\n");
				sb1.append(strPath.get(j)).append("||").append(sortList.get(i)).append("\n").append("        ");
				pathListFinal.add(sb1.toString());
				sb1.delete(0, sb1.length());
			}
		
				
		}
		
		pathsFinal = sb1.toString();
		
		
		
		Paths paths =new Paths(sb.toString());
		paths.setVisible(true);
		sb.delete(0, sb.length());
		return null;
	}

	public int findPathDuration(String startNode, List<String> path, int totalDuration) throws Exception {

		if(nextJob.containsKey(startNode)) {
		if((nextJob.get(startNode).size()==1 && path.contains(nextJob.get(startNode).get(0))) || nextJob.get(startNode).get(0).equals(startNode))
				{
			ErrorScreen error = new ErrorScreen("Loop found");
			error.setVisible(true);
				}
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
	List<String> net = new ArrayList<>();
//		List<Integer> totalDurations = new ArrayList<>();
		for(int i=0;i<path.size();i++)
		{
			totalDuration =totalDuration+durations.get(path.get(i));
			sb.append(path.get(i));
			if(i!=path.size()-1)
				sb.append("-->");
			
		}
		
		if(mapPath.containsKey(totalDuration))
		{
		net = mapPath.get(totalDuration);
		net.add(sb.toString());
		mapPath.put(totalDuration, net);
		}
		else {
			net = new ArrayList<>();
			net.add(sb.toString());
			mapPath.put(totalDuration, net);
		}
		durationList.add(totalDuration);
		
		
		sb = sb.delete(0, sb.length());
		//sb.append("      "+"Duration   ").append(totalDuration).append("\n");
	}

}
