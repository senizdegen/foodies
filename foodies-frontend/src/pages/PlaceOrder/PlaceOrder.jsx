import React, { useContext } from 'react'
import "./PlaceOrder.css"
import { assets } from '../../assets/assets'
import { StoreContext } from '../../context/StoreContext'
import { calculateCartTotals } from '../../util/cartUrils'

const PlaceOrder = () => {
   const {foodList, quantities, setQuantities} = useContext(StoreContext)
   const cartItems = foodList.filter((food) => quantities[food.id] > 0)

   const{subtotal, shipping, tax, total} = calculateCartTotals(cartItems, quantities)

  return (
    <div className='container mt-4'>
        <main>

            <div className="py-5 text-center">
          <img
            className="d-block mx-auto"
            src={assets.logo}
            alt=""
            width="98"
            height="98"
          />
          
        </div>
        
            <div className="row g-5">
          <div className="col-md-5 col-lg-4 order-md-last">
            <h4
              className="d-flex justify-content-between align-items-center mb-3"
            >
              <span className="text-primary">Your cart</span>
              <span className="badge bg-primary rounded-pill">{cartItems.length}</span>
            </h4>

            <ul className="list-group mb-3">
              {cartItems.map((item) => (<li className="list-group-item d-flex justify-content-between lh-sm">
                <div>
                  <h6 className="my-0">{item.name}</h6>
                  <small className="text-body-secondary">Qty: {quantities[item.id]}</small>
                </div>
                <span className="text-body-secondary">&#8376;{item.price * quantities[item.id]}</span>
              </li>))}

              <li className="list-group-item d-flex justify-content-between">
                <div>
                  <span>Shipping</span>
                </div>
                <span className="text-body-secondary">&#8376;{subtotal === 0 ? 0.0 : shipping.toFixed(2)}</span>
              </li>
              <li className="list-group-item d-flex justify-content-between lh-sm">
                <div>
                  <span>Tax (10%)</span>
                </div>
                <span className="text-body-secondary">&#8376;{tax.toFixed(2)}</span>
              </li>
              <li className="list-group-item d-flex justify-content-between">
                <span>Total (TNG)</span>
                <strong>&#8376;{total.toFixed(2)}</strong>
              </li>
            </ul>

          </div>

          <div className="col-md-7 col-lg-8">
            <h4 className="mb-3">Billing address</h4>

            <form className="needs-validation" noValidate>
              <div className="row g-3">
                <div className="col-sm-6">
                  <label htmlFor="firstName" className="form-label" >First name</label>
                  <input
                    type="text"
                    className="form-control"
                    id="firstName"
                    placeholder='Dosymzhan'
                    required
                  />
                </div>

                <div className="col-sm-6">
                  <label htmlFor="lastName" className="form-label">Last name</label>
                  <input
                    type="text"
                    className="form-control"
                    id="lastName"
                    placeholder='Seisen'
                    required
                  />
                  
                </div>

                <div className="col-12">
                  <label htmlFor="email" className="form-label">Email</label>
                  <div className="input-group has-validation">
                    <span className="input-group-text">@</span>
                    <input
                      type="email"
                      className="form-control"
                      id="email"
                      placeholder="Email"
                      required
                    />
                    
                  </div>
                </div>

                <div className="col-12">
                  <label htmlFor="phone" className="form-label">Phone Number</label>
                  <input
                    type="number"
                    className="form-control"
                    id="phone"
                    placeholder="87000000001"
                    required
                  />
                  
                </div>

                <div className="col-12">
                  <label htmlFor="address" className="form-label">Address</label>
                  <input
                    type="text"
                    className="form-control"
                    id="address"
                    placeholder="1234 Main St"
                    required
                  />
                  
                </div>

                <div className="col-md-5">
                  <label htmlFor="country" className="form-label">Country</label>
                  <select className="form-select" id="country" required>
                    <option value="">Choose...</option>
                    <option>Kazakhstan</option>
                  </select>
                  
                </div>

                <div className="col-md-4">
                  <label htmlFor="city" className="form-label">City</label>
                  <select className="form-select" id="city" required>
                    <option value="">Choose...</option>
                    <option>Almaty</option>
                  </select>
                  
                </div>

                <div className="col-md-3">
                  <label htmlFor="zip" className="form-label">Zip</label>
                  <input type="number" className="form-control" id="zip" placeholder='050000' required />
                </div>
              </div>

              <hr className="my-4" />

              <button className="w-100 btn btn-primary btn-lg" type="submit" disabled={cartItems.length === 0}>
                Continue to checkout
              </button>
            </form>
          </div>
        </div>
        </main>
    </div>
  )
}

export default PlaceOrder
