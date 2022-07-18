// For this challenge you will be optimally assigning tasks to a number of machines.
/*
have the function OptimalAssignments(strArr) read strArr which will represent an NxN matrix and
it will be in the following format: ["(n,n,n...)","(...)",...]
where the n's represent integers. This matrix represents a machine at row i performing
 task at column j. The cost for this is matrix[i][j]. Your program should determine what m
 achine should perform what task so as to minimize the whole cost and it should return the pairings of machines to tasks in the following format: (i-j)(...)... Only one machine can perform one task. For example: if strArr is ["(5,4,2)","(12,4,3)","(3,4,13)"] then your program should return (1-3)(2-2)(3-1) because assigning the machines to these tasks gives the least cost. The matrix will range from 2x2 to 6x6, there will be no negative costs in the matrix, and there will always be a unique answer.
*/

public class OptimalAssignments {
}
