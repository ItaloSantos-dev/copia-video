import { User } from "../user";

export interface ReportUserMetrics {
    lastUsers:User[],
    usersWithMostIdeas:User[]
}