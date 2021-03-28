# StudentAPI

This is Simple API to illustrate one to one mapping 

Once we start the app, it will automatically create table schema based on Entity classes.

## Access to H2 console

```
http://localhost:8082/h2-console
```

## Access to endpoint
```
http://localhost:8082/student/all/venki
```
How many times you hit the API , it make sure only one record avaialble in child table due to below code.

`
Optional<Address> existingAddress = addressRepository.findByStudentId(student.getStudentId());
                if(existingAddress.isPresent()){
                    // Do Update existing record
                    address.setCountry("updated country");
                    address.setAddressId(existingAddress.get().getAddressId());
                    BeanUtils.copyProperties(address, existingAddress.get());
                    addressRepository.save(existingAddress.get()); //existing record update
                }else{
                    addressRepository.save(address);
                }
                `
