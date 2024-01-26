import { VotesDto } from './votes-dto';

export interface ParliamentMemberDto {
  active?: boolean;
  birthDate?: string;
  birthLocation?: string;
  club?: string;
  districtName?: string;
  districtNum?: number;
  educationLevel?: string;
  apiID?: number;
  email?: string;
  firstLastName?: string;
  firstName?: string;
  lastFirstName?: string;
  lastName?: string;
  numberOfVotes?: number;
  secondName?: string;
  voivodeship?: string;
  waiverDesc?: string;
  votes?: VotesDto[];
}
