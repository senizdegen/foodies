import React, { useEffect, useState } from 'react';
import {assets} from '../../assets/assets'
import { toast } from 'react-toastify';
import { addFood } from '../../services/foodService';

const AddFood = () => {
  const [image, setImage] = useState(false)
  const [data, setData] = useState({
    name:'',
    description:'',
    price:'',
    category:'Cake'
  });

  const onChangeHandler = (event) => {
    const name = event.target.name;
    const value  = event.target.value
    setData(data => ({...data, [name] : value}))
  }

  // useEffect(() => {
  //   console.log(data)
  // }, [data])

  const onSubmitHandler = async (event) => {
    event.preventDefault();
    if(!image) {
      toast.error('Please select an image');
      return;
    }
    try {
      await addFood(data, image)
      toast.success('Food added successfully.')
      setData({name: '', description: '', category: 'Cake', price: ''})
      setImage(null)
  } catch (error) {
    toast.error('Error while adding food.')
  }
}
  

  return (
    <div className="mx-2 mt-2">
    <div className="row">
      <div className="card col-md-4">
        <div className="card-body">
          <h2 className="mb-4">Add Food</h2>
          <form onSubmit={onSubmitHandler}>

            <div className="mb-3">
              <label htmlFor="image" className="form-label">
                <img src={image ? URL.createObjectURL(image) : assets.upload} alt='' width={98}/>
              </label>
              <input type="file" className="form-control" id="image" hidden onChange={(e) => setImage(e.target.files[0])}/>
            </div>
            
            <div className="mb-3">
              <label htmlFor="name" className="form-label">Name</label>
              <input type="text" placeholder='Napoleon Cake' className="form-control" id="name" required name='name' onChange={onChangeHandler} value={data.name}/>
            </div>
            
            <div className="mb-3">
              <label htmlFor="description" className="form-label">Description</label>
              <textarea className="form-control" placeholder='Write content here...' id="description" rows="5" required name='description' onChange={onChangeHandler} value={data.description}></textarea>
            </div>

            <div className="mb-3">
              <label htmlFor="category" className="form-label" >Category</label>
              <select name='category' id='category' className='form-control' onChange={onChangeHandler} value={data.category}>
                <option value="Cake">Cake</option>
                <option value="Pizza">Pizza</option>
                <option value="Burger">Burger</option>
                <option value="Rolls">Rolls</option>
                <option value="Salad">Salad</option>
                <option value="Ice cream">Ice cream</option>
              </select>  
            </div>

            <div className="mb-3">
              <label htmlFor="price" className="form-label">Price</label>
              <input type="number" className="form-control" placeholder='&#8376;200' id="price" required name='price' onChange = {onChangeHandler} value={data.price}/>
            </div>

            <button type="submit" className="btn btn-primary">Save</button>
          </form>
        </div>
      </div>
    </div>
</div>
  )
}

export default AddFood;
