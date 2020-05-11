package com.danscafe.siteapi.dao;

import com.danscafe.siteapi.model.Profile;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.CloudTableClient;
import com.microsoft.azure.storage.table.TableOperation;

public class ProfileDaoImpl implements ProfileDAO{
    @Override
    public Profile addProfile(Profile profile) {
        try
        {
            String storageConnectionString = "UseDevelopmentStorage=true";
            // Retrieve storage account from connection-string.
            CloudStorageAccount storageAccount =
                    CloudStorageAccount.parse(storageConnectionString);

            // Create the table client.
            CloudTableClient tableClient = storageAccount.createCloudTableClient();

            // Create a cloud table object for the table.
            CloudTable cloudTable = tableClient.getTableReference("people");

            // Create a new customer entity.
            Profile user  = new Profile("USA", "12345");
            user.setUsername(profile.getUsername());
            user.setPassword(profile.getPassword());

            // Create an operation to add the new customer to the people table.
            TableOperation insertCustomer1 = TableOperation.insertOrReplace(user);

            // Submit the operation to the table service.
            cloudTable.execute(insertCustomer1);
        }
        catch (Exception e)
        {
            // Output the stack trace.
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Profile getProfile(String profileID) {
        return null;
    }

    @Override
    public void deleteProfile(String profileID) {
        //Not yet implemented
    }
}
