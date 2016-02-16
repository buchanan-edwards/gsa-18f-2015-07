Rails.application.routes.draw do
  root 'homepage#show'

  resource :searches, only: :show

  resources :medications, only: :show
  resource :errors, only: :show

  match '*a', to: 'errors#show', via: [:get, :post, :put]
end
