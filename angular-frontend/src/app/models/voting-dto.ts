import { ParliamentMemberDto } from './parliamentMember-dto';

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
  totalVotes?: number;
  kind?: string;
  participants?: ParliamentMemberDto[];
}
