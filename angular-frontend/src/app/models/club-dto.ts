import { ParliamentMemberDto } from './parliamentMember-dto'; // Import the missing interface
export interface ClubDto {
  id?: number;
  nameId?: string;
  name?: string;
  membersCount?: number;
  email?: string;
  members?: ParliamentMemberDto[];
}
