/**
 * Temporary interface for user.
 * Will at some point have an image
 * TODO: 1. add image
 */
export interface User {
  id: number;
  userName: string;
  userFirstName: string;
  userLastName: string;
  isEmailAvailable: boolean;
  isTextAvailable: boolean;
}
