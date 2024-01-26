import { VotesDto } from './votes-dto';

export interface VotingDto {
  id?: number;
  date?: string;
  sitting?: number;
  sittingDay?: number;
  title?: string;
  topic?: string;
  yes?: number;
  no?: number;
  abstain?: number;
  totalVoted?: number;
  kind?: string;
  participants?: VotesDto[];
}
