export interface AccountDetails {
  accountId: string;
  balance: number;
  currentPage: number;
  totalPages: number;
  pageSize: number;
  accountOperationDTOS: AccountOperation[];
}

export interface AccountOperation {
  id: number;
  date: Date;
  amount: number;
  type: string;
  description: string;
}

  export interface BankAccount {
      id: string;
      type: string;
      balance: number;
      createdAt: Date;
  }

